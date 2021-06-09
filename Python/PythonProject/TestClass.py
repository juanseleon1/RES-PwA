#! /usr/bin/env python
# -*- encoding: UTF-8 -*-

"""Example: Use onTouchDown Method"""

import easygui


def main():
    """
    This example uses the onTouchDown method.
    To Test ALTabletService, you need to run the script ON the robot.
    """
    # Get the service ALTabletService.
    listChoices = list()
    listChoices.append("Aumentar Estado Emocional")
    listChoices.append("Bajar Estado Emocional")
    listChoices.append("Aumentar Relajacion")
    listChoices.append("Bajar Relajacion")
    listChoices.append("Aumentar Atencion")
    listChoices.append("Bajar Atencion")

    choice = easygui.buttonbox(msg="Escoger", choices=listChoices, title="Simular Evento Emocional")
    while True:
        if choice == "Aumentar Estado Emocional":
            choice = easygui.buttonbox(msg="Escoger", choices=listChoices, title="Simular Evento Emocional")
        elif choice == "Bajar Estado Emocional":
            choice = easygui.buttonbox(msg="Escoger", choices=listChoices, title="Simular Evento Emocional")
        elif choice == "Aumentar Relajacion":
            choice = easygui.buttonbox(msg="Escoger", choices=listChoices, title="Simular Evento Emocional")
        elif choice == "Bajar Relajacion":
            choice = easygui.buttonbox(msg="Escoger", choices=listChoices, title="Simular Evento Emocional")
        elif choice == "Aumentar Atencion":
            choice = easygui.buttonbox(msg="Escoger", choices=listChoices, title="Simular Evento Emocional")
        elif choice == "Bajar Atencion":
            choice = easygui.buttonbox(msg="Escoger", choices=listChoices, title="Simular Evento Emocional")
        else:
            break


if __name__ == "__main__":
    main()
