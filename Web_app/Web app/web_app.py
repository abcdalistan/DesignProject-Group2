from flask import Flask, render_template,request,redirect,url_for
from flask_pymongo import pymongo
app = Flask(__name__)
client = pymongo.MongoClient("mongodb+srv://admin:admin@cluster.kbgpm.mongodb.net/myFirstDatabase?retryWrites=true&w=majority")
db = client.usersDB
uR = db.usersRecords
@app.route("/")
def hello():
    return render_template("web.html")

@app.route("/login",methods = ['GET','POST'])
def login():
    if request.method == 'GET':
        return render_template("login.html")
    else:
        login_ = tuple(uR.find({'$and':[{'username':request.form['username'],'password':request.form['password']}]}).limit(1))
        if login_:
            return redirect(url_for('information',success=True,fname=login_[0]['first_name']))
        else:
            return render_template("login.html",incorrect=True)
@app.route("/register",methods = ['GET','POST'])
def register():
    if request.method == 'GET':
        return render_template("register.html")
    else:
        if tuple(uR.find({'username':request.form['username']}).limit(1)):
            return render_template("register.html",userExists=True)
        else:
            insUser = {'first_name':request.form['fname'],'last_name':request.form['lname'],'username':request.form['username'],'password':request.form['password']}
            uR.insert_one(insUser)
            return render_template("register.html",success=True,fname=request.form['fname'])
@app.route("/about")
def about():
    return render_template("team.html")

@app.route("/information")
def information():
    try:
        return render_template("info.html",success=request.args['success'],fname=request.args['fname'])
    except:
        return render_template("info.html")
@app.route("/monitoring")
def monitoring():
    return render_template("monitoring.html")

@app.route("/plant_data")
def plant_data():
    return render_template("plant-data.html")
    
if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)
 