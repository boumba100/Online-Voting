import ArduinoConnection


class ControllerService:
    def __init__(self, passcode):
        self.passcode = passcode;
        self.arduinoConnection = None

    def connect(self, passcode):
        if passcode == self.passcode:
            return True
        else:
            return False

    def sendScore(self, serverPasscode, clientPasscode, score):
        if serverPasscode == clientPasscode:
            #ArduinoConnection.sendScore(score)
            return "success!"
        else :
            return "wrong passcode!"

                
        
