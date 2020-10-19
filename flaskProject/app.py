import subprocess
from mailcap import show

from flask import Flask, redirect, url_for, render_template, request

app = Flask(__name__)

@app.route("/login", methods=["POST", "GET"])
def login():
    if request.method == "POST":
        source = str(request.form["source"])
    if request.method == "POST":
        destination = str(request.form["destination"])
    if request.method == "POST":
        hour = str(request.form["hour"])
    if request.method == "POST":
        minuts = str(request.form["minuts"])

        return subprocess.check_output(["java", "-classpath", "/home/moshe/newRail/Rail_il_project/src", "railIl/MainUI"
                                ,"html",source,destination,hour,minuts])

    else:
        return render_template("login.html")


@app.route("/<usr>")
def user(usr):
    return f"<h1>{usr}</h1>"


if __name__ == "__main__":
    app.run(debug=True)
