


#remove empty spaces
def remove_if(routeList):
    #used for eleminating emptySpaces
    return [x for x in routeList if x]

#extender
def extend2(route):
    print("route: "+str(route))
    #act else [x.append(route)]
    #route.insert(0,x)
    l = list(map(lambda x: [] if(x in route) else route.append([x]+[route[0]]),vecinos(route[0])))
    return remove_if(l) #route[1:])

def extend(route):
    l = list(map(lambda x: [] if(x in route) else [x]+route,vecinos(route[0])))
    return remove_if(l) 

#solution?
def solution(possibleSolution,members):
    #Condicion de salida = [0,0,members,members,"I"]
    if(possibleSolution[2] == members and possibleSolution[3] == members and
       possibleSolution[4]=="I"):
        return True
    else:
        return False    
    
#prof_aux
def process(routeLists,members):
    if(not routeLists):
        return []
    elif(solution(routeLists[0][0],members)):
        writeArchive(routeLists[0])
        process(extend(routeLists[0]) + routeLists[1:],members)
    else:
        process(extend(routeLists[0]) + routeLists[1:],members)

#Prof
def initial(members):
    process([[[members, members, 0, 0, "D"]]],members)
    readArchive()

def readArchive():
    fo = open("gameAnswer.txt","r")
    strA = fo.read(300)
    print("Final: " + strA)
    fo.close

def writeArchive(path):
    fo = open("gameAnswer.txt", "w+")
    fo.write(str(path))
    fo.close()

def fiesta(lista):
    if((lista[0] >= 0 and lista[1] >= 0 and lista[2] >= 0 and lista[3] >= 0)):#First check to see if the numbers are greater o equal to 0
        if (lista[0] < lista[1] and lista[0] != 0) or (lista[2] < lista[3] and lista[2] != 0):
            return True
        else:
            return False
    else:
        return True

def oneMisionary(lista):

    if lista[4]== "D":
        if fiesta([lista[0]-1,lista[1],lista[2]+1,lista[3]]):##(-1,_,+1,_) One misionary from right to left
            return lista
        else:
            return [lista[0]-1,lista[1],lista[2]+1,lista[3],"I"]#No party
    else:
        if fiesta([lista[0]+1,lista[1],lista[2]-1,lista[3]]):##(+1,_,-1,_) Move on misionary from left to right
            return lista
        else:
            return [lista[0]+1,lista[1],lista[2]-1,lista[3],"D"]

def oneCanibal(lista):
    if lista[4]== "D":
        if fiesta([lista[0],lista[1]-1,lista[2],lista[3]+1]):##(_,-1,_,+1) Move one canibal from right to left
            return lista
        else:
            return [lista[0],lista[1]-1,lista[2],lista[3]+1,"I"] ##It is posible
    else:
        if fiesta([lista[0],lista[1]+1,lista[2],lista[3]-1]):##(_,+1,_,-1) Move one canibal from right to left
            return lista
        else:
            return [lista[0],lista[1]+1,lista[2],lista[3]-1,"D"] ##It is posible movement
        
def oneAndOne(lista):
    if lista[4]== "D":
        if fiesta([lista[0]-1,lista[1]-1,lista[2]+1,lista[3]+1]):##si (-1,-1,+1,+1) Move one and one from right to left
            return lista
        else:
            return [lista[0]-1,lista[1]-1,lista[2]+1,lista[3]+1,"I"] ##valid movement
    else:
        if fiesta([lista[0]+1,lista[1]+1,lista[2]-1,lista[3]-1]):##si (+1,+1,-1,-1) move one and one from left to right
            return lista
        else:
            return [lista[0]+1,lista[1]+1,lista[2]-1,lista[3]-1,"D"] ##Valid movement

def twoMisionaries(lista):
    if lista[4]== "D":
        if fiesta([lista[0]-2,lista[1],lista[2]+2,lista[3]]):##si (-2,_,+2,_) two misionares to left
            return lista
        else:
            return [lista[0]-2,lista[1],lista[2]+2,lista[3],"I"] ##Valid movement
    else:
        if fiesta([lista[0]+2,lista[1],lista[2]-2,lista[3]]):##si (+2,_,-2,_) Two misionares to right
            return lista
        else:
            return [lista[0]+2,lista[1],lista[2]-2,lista[3],"D"] ##Valid movement


def twoCanibals(lista):
    if lista[4]== "D":
        if fiesta([lista[0],lista[1]-2,lista[2],lista[3]+2]):##si (_,-2,_,+2) two canibales to left
            return lista
        else:
            return [lista[0],lista[1]-2,lista[2],lista[3]+2,"I"]
    else:
        if fiesta([lista[0],lista[1]+2,lista[2],lista[3]-2]):##si (_,+2,_,-2) two canibales to right
            return lista
        else:
            return [lista[0],lista[1]+2,lista[2],lista[3]-2,"D"] ##valid movement

def vecinos(lista):
    #retorna una lista de todos los posibles vecinos
    return [oneMisionary(lista),oneCanibal(lista),oneAndOne(lista),twoMisionaries(lista),twoCanibals(lista)]


