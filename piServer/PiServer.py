from flask import Flask, request, session
from flask.ext.cors import CORS
from ControllerService import *
app = Flask(__name__)
CORS(app)
controllerService = None

@app.route('/')
def home():
   return 'rasberryPI node'

@app.route('/connect', methods=['POST'])

def connect():
   passcode = request.form['passcode']
   result = controllerService.connect(passcode)
   if result == True:
      session['passcode'] = passcode
      return 'succes'
   else:
      return "erreur"

@app.route('/sendScore', methods=['POST'])
def sendScore():
    score = request.form['score']
    clientPasscode = request.form['passcode']
    print "trying to show score : " + score
    result = controllerService.sendScore(clientPasscode, score)
    return result

if __name__ == '__main__':
   serverPasscode = raw_input("passcode for server connection :")
   controllerService = ControllerService(serverPasscode)
   app.secret_key = 'asdlfnjklhJKOHIGHUIASGFUIAGSDF'
   app.run(host = '0.0.0.0')

