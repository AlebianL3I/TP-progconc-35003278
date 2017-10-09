from threading import Thread, RLock
from tkinter   import *
from random    import *
from math      import *
import time

colors = ['red','blue','green','yellow']

largeur = 400
hauteur = 370
taille  = 40

class main(Thread):
    def __init__(self):
        Thread.__init__(self)
        #la fenetre
        self.fenetre = Tk()
        self.score   = 0
        self.deamon  = True
        self.pause   = True
        self.temps   = int(time.clock())

        #le texte
        self.t_score = Label(self.fenetre, text = 'Score: {}'.format(self.score))
        self.t_score.pack()

        self.t_temps = Label(self.fenetre, text = 'Temps: {}'.format(self.temps))
        self.t_temps.pack()

        #le canevas
        self.canevas = Canvas(self.fenetre, width = largeur, height = hauteur, bg = 'white')
        self.canevas.pack()

        #les boutons
        self.quitter = Button(self.fenetre, text = 'Quitter', command = self.fenetre.destroy)
        self.quitter.pack(side = BOTTOM)

        self.ajout   = Button(self.fenetre, text = '+', command = self.ajout)
        self.ajout.pack(side = LEFT)

        self.effacer = Button(self.fenetre, text = '-', command = self.effacer)
        self.effacer.pack(side = RIGHT)

        self.b_pause = Button(self.fenetre, text = 'Pause', command = self.pause)
        self.b_pause.pack(side = TOP)
        
    def run(self):
        pass

    def ajout(self):
        x = randint(taille, largeur-taille)
        y = randint(taille, hauteur-taille)
        color = choice(colors)
        new = self.canevas.create_oval(x, y, x+taille, y+taille, fill = color)
        ball(new, x, y, color)

    def effacer(self):
        if ball.liste != []:
            l_canevas = self.canevas.find_all()
            self.canevas.delete(l_canevas[len(l_canevas)-1])
            ball.liste.pop(len(ball.liste)-1)

class ball(object):
    liste = list()
    
    def __init__(self, new, x, y, color = choice(colors)):
        ball.liste.append(self)
        self.new  = new
        self.x    = x
        self.y    = y
        self.dirx = randint(-1, 1)
        self.diry = randint(-1, 1)
        self.color = color
        while self.dirx == 0 and self.diry == 0:
            self.dirx = randint(-1, 1)
            self.diry = randint(-1, 1)
            
    def update(self, p):
        x = p.x - self.x
        y = p.y - self.y
        distance = x*x + y*y
        if (sqrt(distance)) <= (taille):
            fenetre.score += 2
            fenetre.t_score['text'] = ('Score: {}'.format(fenetre.score))
            fenetre.canevas.delete(self.new)
            ball.liste.remove(self)
            fenetre.canevas.delete(p.new)
            ball.liste.remove(p)

class calcul(Thread):
    def __init__(self):
        Thread.__init__(self)
        self.deamon = True
        self.pause  = True

    def run(self):
        while 1:
            fenetre.t_temps['text'] = ('Temps: {}'.format(int(time.clock())))
            if self.pause:
                for i in ball.liste:
                    fenetre.canevas.coords(i.new, i.x+i.dirx, i.y+i.diry, i.x+i.dirx+taille, i.y+i.diry+taille)
                    i.x += i.dirx
                    i.y += i.diry
                    if i.x == 0 or i.x == largeur-taille:
                        i.dirx = -i.dirx
                    if i.y == 0 or i.y == hauteur-taille:
                        i.diry = -i.diry
                    for element in ball.liste:
                        if element!=i:
                            element.update(i)
            time.sleep(0.02)

fenetre  = main()
f_calcul = calcul()

fenetre.start()
f_calcul.start()
fenetre.fenetre.mainloop()
fenetre.pause = False
                        
            
            
        






























    
