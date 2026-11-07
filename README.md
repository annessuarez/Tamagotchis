# Tamagochis
Aplicación donde el programa principal (**Cuidador**) gestiona la vida y acciones de varios **Tamagochis** independientes.

---
## Ramas del proyecto

* **`main`**: Rama principal con el proyecto final entregable.
* **`ProyectoComentarios`**: Rama secundaria con notas y apuntes personales para el examen.

> [!NOTE]  
> Para la corrección, tomar como referencia únicamente la rama **`main`**.

---

## Requisitos Principales

* Cada Tamagochi tiene su propio hilo de vida y un identificador único. No se comunican entre ellos, solo con el cuidador.
* No pueden realizar dos actividades al mismo tiempo.
* Mueren automáticamente a los **5 minutos** con un aviso previo.

---

## Acciones y Reglas

* **Comer:** Cada uno come a velocidad diferente. Avisan al empezar y al terminar.
* **Jugar:** Plantean una suma de dos números de 1 cifra. El cuidador debe responder; si falla, siguen jugando.
* **Higiene (Escala 0 a 10):**
  * Sube **+1 de suciedad cada 20 segundos**.
  * Al llegar a **5**, avisa de que está muy sucio.
  * Al llegar a **10**, muere.
  * Bañarlo tarda **5 segundos** y reinicia la suciedad a **0**.
* **Control del cuidador:**
  * Puede consultar el estado de cada criatura en cualquier momento.
  * Puede ordenar acciones (comer, jugar, bañar).
  * Puede eliminarlos, pero **solo si están ociosos**.
