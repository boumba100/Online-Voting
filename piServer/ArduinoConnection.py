import serial


class ArduinoConnection:
    def __init__(self, port = 'COM4'):
        self.port = port
        self.serialConnection = serial.Serial(self.port, 9600);

    def sendScore(self, score):
        self.serialConnection.write(score)
        self.serialConnection.flush()
        
