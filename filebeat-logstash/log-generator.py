#!/usr/bin/env python3
import argparse
import json
import os
import random
import re
import time
from datetime import datetime

parser = argparse.ArgumentParser()
parser.add_argument(
    '--format',
    default='text',
    required=True
)
args = parser.parse_args()

active_log_fname = 'demo.log'
lines = []
f = False
# Basic, single-line log format
if 'text' == args.format:
    request_string = '[{date_and_time}] request from pid {pid}\n'
    response_string = '[{date_and_time}] response for pid {pid}\n'
# JSON with some object metadata
elif 'json' == args.format:
    request_string = '''
{date_and_time}; pid={pid}: request received: {{
    "request" : {{
        "server" : "https://demo.example.com/",
        "actions" : {{
            "garbageCollect" : {{
                "existingAgents" : [
                    "do-not-use"
                ]
            }}
        }},
        "noOp" : false
    }},
    "parameters" : {{
        "capabilities" : {{
            "basic" : true
        }},
        "cert" : "server",
        "request-method" : "POST",
        "service" : "agent"
    }}
}}
'''
    response_string = '''
{date_and_time}; pid={pid}: Response: {{
    "errors": [

    ],
    "completed": {{
        "garbageCollect": {{
        "ec2": [

        ],
        "ravello": [

        ]
        }},
        "create": [

        ]
    }}
}}
'''
else:
    raise(Exception)

def lprint(s):
    with open(active_log_fname, 'a') as f:
        f.write(s)

if os.path.exists(active_log_fname):
    os.remove(active_log_fname)
while True:
    request_pid = random.randint(10000, 99000)
    lprint(
        request_string.format(
            date_and_time=datetime.now().ctime(),
            pid=request_pid
        )
    )
    time.sleep(4)
    lprint(
        response_string.format(
            date_and_time=datetime.now().ctime(),
            pid=request_pid
        )
    )
    time.sleep(4)
