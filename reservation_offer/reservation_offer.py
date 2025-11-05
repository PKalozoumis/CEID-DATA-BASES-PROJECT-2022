#A simple script for creating 60000 offer reservations
#The client names are provided by names.txt by scrambling first and last names
#The reservations are spread evenly across the 3 offers

import mysql.connector as mysql
from dotenv import load_dotenv
import os
import random

load_dotenv("../.env")

#Connect to database
#--------------------------------------------------------------
conn = mysql.connect(
	host=os.getenv('DB_HOST'),
    port=os.getenv('DB_PORT'),
    user=os.getenv('DB_USER'),
    password=os.getenv('DB_PASS'),
    database=os.getenv('DB_NAME')
)

cursor = conn.cursor()

#Read names
#--------------------------------------------------------------
names = []
lnames = []
with open("names.txt", "r", encoding="utf-16") as f:
    for i, line in enumerate(f):
        temp = line.strip().split("\t")
        names.append(temp[0])
        lnames.append(temp[1])

#Create data
#--------------------------------------------------------------
data = [] 
for i in range(60000):
	data.append((
		None,
		str(int(i/20000) + 1).rjust(11, "0"),
        names[random.randint(0, 9999)],
        lnames[random.randint(0, 9999)],
        random.random()*150 + 50
	))
        
#Database requests and cleanup
#--------------------------------------------------------------
cursor.execute("DELETE FROM reservation_offer")
cursor.execute("ALTER TABLE reservation_offer AUTO_INCREMENT = 0")
cursor.executemany("INSERT INTO reservation_offer VALUES (%s, %s, %s, %s, %s)", data)
conn.commit()

cursor.close()
conn.close()