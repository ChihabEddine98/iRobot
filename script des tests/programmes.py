from random import randint

import os

actions=["Tourner","Avancer"]
expressions=["Lire","nb","("]
operations=["+","-"]


def genererProgramme(nbLignes):

    cpt=0
    prg=""
    for i in range(0,nbLignes-1):
        prg += genererInstruction() + "; "
        cpt+=1
        if(cpt%2==0):
            prg+="\n"



    prg+="Ecrire("+genererExpression()+");"

    return prg



def genererInstruction():

    action=actions[randint(0,1)]+"("

    instruction=action+genererExpression()+")"

    return instruction


def genererExpression():

    rand=randint(0,2)
    exp=expressions[rand]
    op=operations[randint(0,1)]

    if(exp=="nb"):
        exp=str(randint(-25,50))
    elif(exp=="("):
        exp1=genererExpression()
        exp2=genererExpression()

        exp="("+exp1+op+exp2+")"


    expression=exp


    return expression


def genererGrilles(height,width):

    direction=randint(0,3)
    x=randint(0,width-1)
    y=randint(0,height-1)

    grille=str(width)+" "+str(height)+" "+str(x)+" "+str(y)+" "+str(direction)
    grille += "\n"

    for i in range(0,height):
        for j in range(0,width):
            grille+=str(randint(-1,9))+" "

        grille+="\n"




    return grille





def genererFichiers(nbFichiersProg, nbIns,nbFichGrilles):
    if not os.path.exists("testsGood"):
        os.makedirs("testsGood")


    for i in range(0, nbFichiersProg):
        fichier= open("testsGood/good"+str(i+1)+".txt", "w")
        fichier.write(genererProgramme(nbIns))

    if not os.path.exists("testsGood/grillesTest"):
        os.makedirs("testsGood/grillesTest")

    for i in range(0, nbFichGrilles):
        fichier= open("testsGood/grillesTest/grille"+str(i+1)+".txt", "w")
        fichier.write(genererGrilles(randint(3,6),randint(3,10)))


nbFiles=int(input("  Entrez le nombre de fichiers tests à generer          :"))
nbLignes=int(input(" Entrez le nombre de instructions Dans chaque fichier  :"))
nbgrilles=int(input(" Entrez le nombre de grilles                          :"))



genererFichiers(nbFiles,nbLignes,nbgrilles)