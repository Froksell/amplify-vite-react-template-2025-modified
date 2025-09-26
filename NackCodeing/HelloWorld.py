import tkinter as tk
from tkinter import messagebox
import p


def helloWorld():
    etikett.config(text="Hello world!")
    messagebox.showinfo("Hello", f"HELLO WORLD")

def ByeWorld():
    etikett.config(text="Bye World!")
    messagebox.showinfo("Bye", f"BYE WORLD")

root = tk.Tk()
root.title("Hello and Bye")
root.geometry("400x250")

etikett = tk.Label(root, text="Choice")


etikett.pack




button = tk.Button(root, text="Hello", command=helloWorld)

button1 = tk.Button(root, text="Bye", command=ByeWorld)

button.pack()
button1.pack()

etikett = tk.Label(root, text="Hello!")
etikett1 = tk.Label(root, text="Bye!")
etikett.pack()
etikett1.pack

root.mainloop()

