import ArduinoConnection


class ControllerService:
    def __init__(self, passcode):
        self.passcode = passcode;
        self.arduinoConnection = ArduinoConnection.ArduinoConnection()

    def connect(self, passcode):
        if passcode == self.passcode:
            return True
        else:
            return False

    def sendScore(self, clientPasscode, score):
        if self.passcode == clientPasscode:
            self.arduinoConnection.sendScore(score)
            return "success!"
        else :
            return "wrong passcode!"

                
        
