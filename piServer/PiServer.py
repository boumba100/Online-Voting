from flask import Flask, request, send_from_directory
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
   print "trying to connect"
   passcode = request.form['passcode']
   result = controllerService.connect(passcode)
   if result == True:
      return 'succes'
   else:
      return "erreur"

@app.route('/sendScore', methods=['POST'])
def sendScore():
    print request.form['score']
    return 'hello'

if __name__ == '__main__':
   serverPasscode = raw_input("passcode for server connection :")
   controllerService = ControllerService(serverPasscode)   
   app.run(host= '0.0.0.0')

