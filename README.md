# RES-PwA
El Alzheimer es una condición neurológica progresiva que afecta en gran medida la realización de actividades de la vida diaria de quien lo padece, lo que implica la presencia permanente de un cuidador.  Con el paso de los años, se espera que la cantidad de personas con Alzheimer aumente a mayor velocidad que la cantidad de cuidadores, lo que representa un problema; ya que no se podrá ofrecer al paciente ni la atención ni el cuidado personalizado que requiere.

Ante esta problemática, se desarrolló el sistema RES-PwA (Robot Emotional Support for People With Dementia), cuyo objetivo es brindar soporte emocional personalizado a adultos mayores que padecen del Alzheimer en estadios leves y moderados. El sistema está implementado utilizando la arquitectura de agentes BDI-CHA propuesta por el grupo SIRP de la Pontificia Universidad Javeriana y hace uso del Robot Pepper de SoftBank Robotics. RES- PwA está en la capacidad de realizar actividades como estimulación musical y cuentería, siguiendo el criterio del estado emocional del usuario. La solución fue validada y verificada por expertos en los campos de gerontología, psicología, interacción humano robot y cuidado de adultos mayores. 

Estructura del Repositorio:
* Android: Implementacioó inicial del modulo intermediario a partir de QiSDK para el SO Android.
* BESA: Implementación del modulo RES-PwA Agent, a partir de la extensión del framework BESA-BDI
* Python: Implementación del modulo Python Utilities, a partir de la SDK qi para Python.
* Topicos: Archivos .top con los topicos basados en ALDialog.
* scriptsDocker: Scripts para la gestion de la imagen OracleDB.
