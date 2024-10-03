# Memoria del Proyecto

## 1. Análisis de la estructura del proyecto

El proyecto está distribuido en diferentes carpetas:

- **Kotlin+java**: Contiene las clases definidas y los tests creados, incluyendo las Activities.
- **manifests**: Alberga la descripción del proyecto, donde se definen las activities.
- **res**: Contiene el contenido visual de la aplicación, como los layouts definidos y la estructura de la app.
- **Gradle Scripts**: Incluye todos los ajustes y scripts del proyecto y sus módulos.

Las actividades están compuestas por:
1. La clase en Kotlin.
2. La creación del XML del layout.
3. La declaración en el Manifest.

Por lo tanto, no solo necesitamos la Clase y el Layout, sino también la declaración/definición en el manifest.
## 2. Análisis del ciclo de vida y el problema de la pérdida de estado

Tras investigar la funcionalidad del Contador, se identificó que al cambiar el estado de la actividad (por ejemplo, al rotar la pantalla), los datos mantenidos en la actividad se pierden.

### Flujo de la aplicación:
1. Generación inicial del layout de Main.
2. Un listener para esperar el clic del usuario en el botón mostrado.
3. Al hacer clic, se genera el layout de MostraComptadorActivity.
4. El nuevo layout permite volver al layout Main original.


## 3. Solución a la pérdida de estado

Para solucionar el error comentado del estado, podemos realizar lo siguiente.
- Realizamos un overrida a la funcion de la actividad de onSaveInstanceState
- Para que al haber un cambio en la actividad o al ser destruida guardar los datos que queramos para posteriormente podamos recuperarlos.
- override fun onSaveInstanceState(outState: Bundle) {
-    super.onSaveInstanceState(outState)
-    outState.putInt("contador",comptador)
}
  En este caso guardar en un Integer llamado contador el valor del contador que tenemos en el momento del borrado de la actividad.
  Posteriormente nos dirijimos al onCreate y buscamos si existe un valor guardado savedInstanceState para recuperarlo y comenzar con ese valor.

- if (savedInstanceState != null) {
-   comptador = savedInstanceState.getInt("contador",0)
- }


## 4. Ampliando la funcionalidad con decrementos y Reset

Para las dos nuevas funcionalidades de Decrementar y resetear el contador he añadido dos nuevos listener desde el MainActivity.kt para ambos botones del layout.

## val btnRest = findViewById<Button>(R.id.btSubtract)
## val btnReset = findViewById<Button>(R.id.btReset)

##      btnRest.setOnClickListener{
##            comptador--
##            textViewContador.text=comptador.toString()
##        }

##        btnReset.setOnClickListener{
##            comptador = 0
##            textViewContador.text=comptador.toString()
##        }



## 5. Cambios para implementar el View Binding

Para implementar el viewBinding lo primero que hemos realizado es la inserción en el manifest del objecto BuildFeatUre con el viewBinding = true.
Posteriormente haremos una sincronización y un Make project desde Android studio.
Cuando se realice el Make Project declararemos la variable binding en el MainActivity para poder utilizarlo (importaremos el paquete que nos indica).
En el onCreate vamos definir el binding sobre el inflate de la clase generada por kotlin para posteriormente utilizarlo y sustituir el findViewById.
