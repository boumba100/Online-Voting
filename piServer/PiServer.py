from flask import Flask, request, send_from_directory
from ControllerService import *
app = Flask(__name__)
controllerService = None

@app.route('/')
def home():
   return 'rasberryPI node'

@app.route('/connect', methods=['POST'])
def connect():
   passcode = request.form['score']
   result = controllerService.connect(passcode)
   if result == true:
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
   app.run()

