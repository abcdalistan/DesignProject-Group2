from flask import Flask, render_template 
 
app = Flask(__name__)

#@app.route("/")
#def hello():
#    return render_template("web.html")

#@app.route("/index")
#def next():
#    return render_template("index.html")

@app.route("/")
def trial():
    return render_template("plant-data.html")
    
if __name__ == "__main__":
    app.run(debug=True)
 