# coding: utf-8

from threading import *
import sys
import time
from queue import Queue
from random import randint
from tkinter import *


class File():
	def __init__(self):
		self.tab = []
		self.cond = Condition()

	def add(self,nb):
		with self.cond:
			while len(self.tab) >= 20:
				self.cond.wait()
			self.tab = self.tab + [nb]
			print("++ Ajout de " + str(nb))
			self.cond.notifyAll()

	def pop(self):
		with self.cond:
			while len(self.tab) == 0:
				self.cond.wait()
			print("-- Retrait de " + str(self.tab.pop(0)))
			self.cond.notifyAll()


class Prod(Thread):
	def __init__(self,time,file):
		Thread.__init__(self)
		self.time = time
		self.file = file

	def run(self):
		while True:
			self.file.add(randint(1,100))
			time.sleep((self.time)/1000.0)

class Cons(Thread):
	def __init__(self,time,file):
		Thread.__init__(self)
		self.time = time
		self.file = file

	def run(self):
		while True:
			self.file.pop()
			time.sleep((self.time)/1000.0)


class Application(Frame):
    def __init__(self, master=None):
        super().__init__(master)
        self.master = master
        self.pack()
        self.create_widgets()
        self.file = File()

    def create_widgets(self):
        self.btnStart = Button(self)
        self.btnStart["text"] = "Start"
        self.btnStart["command"] = self.start
        self.btnStart.pack(side="bottom")

        self.afficheFile = Label(self, text="File")
        self.afficheFile.pack(side="top")

        self.affProd = Label(self, text="Producteur")
        self.affProd.pack()
        self.affCons1 = Label(self, text="Consommateur 1")
        self.affCons1.pack()
        self.affCons2 = Label(self, text="Consommateur 2")
        self.affCons2.pack()

    def start(self):
        self.btnStart["state"] = DISABLED
        self.p = Prod(300,self.file)
        self.p.daemon = True
        self.p.start()
        self.c1 = Cons(800,self.file)
        self.c1.daemon = True
        self.c1.start()
        self.c2 = Cons(800,self.file)
        self.c2.daemon = True
        self.c2.start()


app = Application(master=Tk())
app.master.title("Exercice 2.3")
app.master.minsize(600, 300)


if __name__ == "__main__":

	input("")
