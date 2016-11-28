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
        
        
