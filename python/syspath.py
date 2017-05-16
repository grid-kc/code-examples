#!/usr/bin/env python3
import os, sys, subprocess

# --- Import submodules via relpath ---
# I don't think this is the standard way to do this anymore, but it's the way I
# know how at this moment
sys.path.append(
    os.path.join(
        os.path.dirname(
            os.path.realpath(__file__)
        ), 'pathnames'
    )
)
print(sys.path)
import path_options
# --- End Import ---

po = path_options.Demo()
po.print_path_diff()
