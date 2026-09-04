# Semana 2 · Desafío Java: miniaplicación de consola bajo restricciones (special)

Variante de [`teletubbies-week2-challenge`](https://github.com/chioooo/teletubbies-week2-challenge)
con el **mismo reto y las mismas restricciones**, pero **sin los comentarios `TODO`** guía
en el código — solo la jerarquía de clases y los métodos abstractos que hacen falta para
que las restricciones tengan sentido (p. ej. `Product.shippingCost`/`taxRate`). El resto
del diseño (campos, constructores, cómo calcula cada cosa) queda completamente abierto,
sin pistas en los comentarios.

Repo aparte para el desafío de cierre de la **semana 2** del programa de formación de
practicantes iWA ([`training-teletubbies`](https://github.com/)), que esa semana cubrió:
herencia/polimorfismo/composición, colecciones/genéricos/`equals`/`hashCode`, y
excepciones/depuración/código limpio/JUnit.

**Objetivo:** diseñar y construir, desde cero y bajo restricciones explícitas, una
miniaplicación de consola que integre los tres temas de la semana.

**Resultado esperado:** miniaplicación funcional con diseño, pruebas y explicación del
código.

## Formato — 20 · 75 · 15 · 10

| Tramo | Minutos | Contenido |
|---|---|---|
| Encuadre | 20 | Presentación del reto y las restricciones |
| Reto práctico | 75 | Diseñar e implementar el carrito de compras |
| Resolución individual | 15 | Terminar / pulir la solución por cuenta propia |
| Tarea | 10 | Preparar la explicación del código (se presenta la próxima sesión) |

## El reto

Enunciado completo, restricciones y rúbrica: [`reto/enunciado.md`](reto/enunciado.md).

## Proyecto Maven

[`shop-cart-app/`](shop-cart-app) — proyecto Maven con JUnit 5 configurado y la jerarquía
de clases como stubs, sin TODOs.

```
shop-cart-app/
├── pom.xml                         # JUnit 5 + surefire + exec-maven-plugin
├── mvnw / mvnw.cmd                 # Maven wrapper: no hace falta tener mvn instalado
└── src/
    ├── main/java/org/example/      # Product, PhysicalProduct, DigitalProduct, Cart,
    │                                # InsufficientStockException, InvalidQuantityException,
    │                                # Main — stubs sin implementación ni TODOs
    └── test/java/org/example/      # CartTest: 4 pruebas nombradas, sin cuerpo ni TODOs
```

### Compilar y correr

```bash
cd shop-cart-app

./mvnw compile exec:java     # corre Main
./mvnw test                  # corre las pruebas
```

(En Windows: `mvnw.cmd`. Con `mvn` instalado: `mvn` en vez de `./mvnw`.)
