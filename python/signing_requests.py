#!/usr/bin/env python3
import argparse
import hashlib
import json
import requests
from base64 import b64encode
from cryptography.hazmat.backends import default_backend
from cryptography.hazmat.primitives import hashes
from cryptography.hazmat.primitives import serialization
from cryptography.hazmat.primitives.asymmetric import padding

parser = argparse.ArgumentParser()
parser.add_argument(
    '--distribution',
    required=True
)
parser.add_argument(
    '--key-pair-id',
    required=True
)
parser.add_argument(
    '--object',
    required=True
)
parser.add_argument(
    '--expiration-time',
    default=1795743914 #Thu Nov 26 20:45:14 EST 2026
)
parser.add_argument(
    '--key-file',
    default=None
)
args = parser.parse_args()

base_url = 'https://{distribution}.cloudfront.net/{obj}'.format(
    distribution=args.distribution,
    obj=args.object
)
policy = '''
{{
    "Statement": [
        {{
            "Condition": {{
                "DateLessThan": {{
                    "AWS:EpochTime": {expiration_time}
                }}
            }},
            "Resource": {base_url}
        }}
    ]
}}
'''.format(
    expiration_time=args.expiration_time,
    base_url=base_url
).replace(' ', '').replace('\n', '')

def create_signed_cf_url(key):
    # This doesn't work, but I have no idea why
    signer = key.signer(
        padding.PKCS1v15(),
        hashes.SHA1()
    )
    signer.update(policy.encode('utf-8'))
    signature = signer.finalize()
    # Encode things correctly
    sig_b64 = b64encode(signature).decode('utf-8')
    sig_b64 = sig_b64.replace('+', '-')
    sig_b64 = sig_b64.replace('=', '_')
    sig_b64 = sig_b64.replace('/', '~')
    url =\
        '{base_url}?Expires={expiration_time}&'\
        'Signature={sig}&Key-Pair-Id={key_pair_id}'.format(
            base_url=base_url,
            expiration_time=args.expiration_time,
            sig=sig_b64,
            key_pair_id=args.key_pair_id)
    return url

# Do the stuff
if args.key_file:
    key = None
    with open(args.key_file, 'rb') as key_file:
        key = serialization.load_pem_private_key(
            key_file.read(),
            password=None,
            backend=default_backend()
        )
    if not key:
        raise Exception('Key didn\'t work for some reason')
    url = create_signed_cf_url(key)
    r = requests.get(url)
    print(url)
    print(r.headers)
    print(r.text)

