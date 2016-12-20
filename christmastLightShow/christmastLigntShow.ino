int ledPins[] = {
  2,3,4,5,6,7,8,9,10,11};

void (*simpleLightPatterns[])() = {
  oneAfterAnother, oneAfterAnotherReverse, oneAtATime, snake, halfHalf, flashAll, inOut};


void setup() {
  for (int i = 0; i < 10; i++) {
    pinMode(ledPins[i], OUTPUT);
  }
  Serial.begin(9600);
}

void loop() {
  if (Serial.available() > 0) {
    String score = Serial.readString();
    if(score == "33") { //33 is for random light show
     generateRandomShow(); 
    }
    if (score == "1") {
      for(int i =0; i < 3; i ++) {
        simpleLightPatterns[0]();
        simpleLightPatterns[1]();
      }
    } 
    else if(score == "2") {
      simpleLightPatterns[1]();
    } 
    else if(score == "3") {
      simpleLightPatterns[2]();
    } 
    else if(score == "4") {
      simpleLightPatterns[3]();
    } 
    else if(score == "5") {
      simpleLightPatterns[4]();
    } 
    else if(score == "6") {
      simpleLightPatterns[5]();
    }
  }
}


void generateRandomShow() {
 for(int i = 0; i <=5; i ++) {
  int randomPattern = random(0, 5);
  simpleLightPatterns[randomPattern]();
 } 
}

void oneAfterAnother() {
  for (int i = 0; i <= 10; i++) {
    digitalWrite(ledPins[i], HIGH);
    delay(200);
  }
  for (int i = 10; i >= 0; i--) {
    digitalWrite(ledPins[i], LOW);
    delay(200);
  }

}

void oneAfterAnotherReverse() {
  for (int i = 10; i >= 0; i--) {
    digitalWrite(ledPins[i], HIGH);
    delay(200);
  }
  for (int i = 0; i <= 10; i++) {
    digitalWrite(ledPins[i], LOW);
    delay(200);
  }
}

void oneAtATime() {
  for (int i = 0; i <= 10; i++) {
    if(i != 0) {
      digitalWrite(ledPins[i - 1], LOW);
    }
    digitalWrite(ledPins[i], HIGH);
    delay(200);
  }
  for (int i = 10; i >= 0; i--) {
    if(i != 10) {
      digitalWrite(ledPins[i + 1], LOW);
    }
    digitalWrite(ledPins[i], HIGH);
    delay(200);
    digitalWrite(ledPins[i], LOW);
  }

}


void snake(){
  for(int i = 0; i <= 2; i ++) {
    for(int i = 0; i <= 10; i++){        
      digitalWrite(ledPins[i], HIGH); 
      delay(200);
    }
    for(int i = 0; i <= 10; i++){        
      digitalWrite(ledPins[i], LOW); 
      delay(200);
    }
  }
}


void halfHalf() {
  for(int i = 0; i <= 2; i ++) {
    for(int i = 0; i <= 4; i ++) {
      digitalWrite(ledPins[i], HIGH);
    }
    delay(200);
    for(int i = 0; i <= 4; i ++) {
      digitalWrite(ledPins[i], LOW);
    }
    for(int i = 5; i <= 10; i ++) {
      digitalWrite(ledPins[i], HIGH);
    }
    delay(200);
    for(int i = 0; i <= 10; i ++) {
      digitalWrite(ledPins[i], LOW);
    }
  }
}

void flashAll() {
  for(int i = 0; i < 4; i ++) {
    for(int i = 0; i <= 10; i ++) {
      digitalWrite(ledPins[i], HIGH);
    }
    delay(200);
    for(int i = 0; i <= 10; i ++) {
      digitalWrite(ledPins[i], LOW);
    }
    delay(200);
  }
}

void inOut() {
  for(int i = 0; i <= 3; i ++) {
    for(int i = 0; i <= 8; i += 2) {
      digitalWrite(ledPins[i], HIGH); 
    }
    delay(200);
    for(int i = 0; i <= 8; i += 2) {
      digitalWrite(ledPins[i], LOW); 
    }
    delay(200);
    for(int i = 9; i >= 0; i -= 2) {
      digitalWrite(ledPins[i], HIGH); 
    }
    delay(200);
    for(int i = 9; i >= 0; i -= 2) {
      digitalWrite(ledPins[i], LOW); 
    }
    delay(200);
  }
}




























