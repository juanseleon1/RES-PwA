from naoqi import ALProxy
import socket
import threading
import re
import json
import cPickle as pickle

def handle_client(conn, addr):
    print("Lo logro")
    connected = True
    while connected:
        msg_length = conn.recv(HEADER)
        msg_length = msg_length.decode(FORMAT, 'ignore')
        
        msg_length = safe_str(msg_length)
        #if msg_length:
        y = safe_str(msg_length).split('{')[1]
        print("msg:")
        y = "{" + y
        #print(y)
        jsonObj = json.loads(y)
        #x = pickle.loads(y)
        #assert x == simple
        
        msg_length = len(jsonObj)
        msg = conn.recv((msg_length)).decode(FORMAT, 'ignore')

        
        
        #jsonObj = json.loads(msg)
        
        #if msg == DISCONNECT_MESSAGE:
         #   connected = False
            #conn.close()
        print(jsonObj["methodName"])
        
        connected = False

def safe_str(obj):
    try: return str(obj)
    except UnicodeEncodeError:
        return obj.encode(FORMAT, 'ignore').decode(FORMAT)
    return ""



def read_obj(self):
    size = self._msg_length()
    data = self._read(size)
    frmt = "=%ds" % size
    msg = struct.unpack(frmt, data)
    return json.loads(str(msg[0],'ascii'))
	    
print("Server starting...pop")
HOST = '127.0.0.1' #socket.gethostbyname(socket.gethostname()) # Standard loopback interface             address (localhost)
print("Server starting on", HOST)
PORT = 7895    # Port to listen on (non-privileged ports are > 1023)
print("Server starting...pop0000000000000000")
ADDR = (HOST, PORT)
server = None
HEADER = 1024
FORMAT = 'utf-8'
DISCONNECT_MESSAGE = "!DISCONNECT"
print("Server starting...pop1111111111111111111")
server = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

print("Server starting...pop22222222222222222222222")
ADDR = (HOST, PORT)
print("Server starting...pop3333333333333333333333333")
server.bind(ADDR)
print("Server starting...pop4444444444444444444444444444")
server.listen(5)
print("[STARTING] server is listening on", HOST)
conn, addr = server.accept()
thread = threading.Thread(target=handle_client, args=(conn, addr))
thread.start()
