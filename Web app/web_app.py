from flask import Flask, render_template 

app = Flask(__name__)

@app.route("/")
def hello():
    return render_template("web.html")

@app.route("/about")
def about():
    return render_template("team.html")

@app.route("/information")
def information():
    return render_template("info.html")

@app.route("/monitoring")
def monitoring():
    return render_template("monitoring.html")

@app.route("/plant_data")
def plant_data():
    return render_template("plant-data.html")
    
if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)
 