#!/usr/bin/env python3
import sys, os

class Demo:
    def init(self):
        pass

    def print_path_diff(self):
        my_dir = os.path.dirname(os.path.realpath(__file__))
        print('--- STUFF ---')
        print('sys.path[0] = {}'.format(sys.path[0]))
        print('os.path.realpath(__file__) = {}'.format(my_dir))
