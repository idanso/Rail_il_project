#!/usr/bin/python
import subprocess

from flask import Flask, request

app = Flask("my_app1")

@app.route("/rail")
def rail_schedule():
    if 'outformat' in request.args:
        outformat = request.args.get('outformat')
    else:
        outformat = "html"
    if 'source' in request.args:
        source = str(request.args.get('source'))
    else:
        source = "tal-aviv"
    if 'destination' in request.args:
        destination = str(request.args.get('destination'))

    else:
        destination = "Beer-sheva"
    if 'hour' in request.args:
        hour = str(request.args.get('hour'))
    else:
        hour = "12"
    if 'minuts' in request.args:

        minuts = str(request.args.get('minuts'))

    else:
        minuts = "00"

    return subprocess.check_output(["java", "-classpath", "/home/moshe/rail_il/Rail_il_project/src", "railIl/MainUI"
                                       , outformat, source, destination, hour, minuts])
