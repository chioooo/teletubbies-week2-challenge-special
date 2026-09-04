# Desafío Java: miniaplicación de consola bajo restricciones

**Dominio:** carrito de compras.

Vas a construir, desde cero, un mini sistema de carrito de compras de consola. No hay
defectos plantados que encontrar — el reto es **diseñar** la solución tú mismo, respetando
un conjunto de restricciones que obligan a usar lo que vimos esta semana (herencia,
polimorfismo, composición, colecciones, `equals`/`hashCode`, excepciones y JUnit).

## Qué debe hacer

Es una app de consola, pero **no necesita ser interactiva** (no hace falta `Scanner` ni
pedir input al usuario). Con que `Main` arme unos productos y un carrito a mano, y
`System.out.println` muestre el resultado, es suficiente — es tu banco de pruebas manual,
lo importante de verdad son las clases del dominio y las pruebas JUnit.

### 1. `Product` y sus dos tipos

Todo producto tiene, como mínimo: un **SKU** (identificador único), un **nombre**, un
**precio unitario** y un **stock disponible**. Hay al menos dos tipos concretos, con
comportamiento distinto — los valores/fórmulas exactos (tasa de impuesto, costo de envío)
los decides tú, pero el comportamiento pedido es:

- **`PhysicalProduct`** — tiene un costo de envío que depende de la cantidad comprada
  (por ejemplo, un monto fijo por unidad, o un fijo + variable — tú decides la fórmula).
- **`DigitalProduct`** — costo de envío **cero** siempre, pero con una tasa de impuesto
  distinta a la de `PhysicalProduct` (por ejemplo, digital paga más o menos IVA que
  físico — tú decides el valor, pero debe ser *distinto* entre los dos tipos para que se
  note en el total).

### 2. `Cart`

- Se le agregan productos indicando **cuántas unidades** quieres.
- Agregar el **mismo producto dos veces** (mismo SKU) debe **acumular la cantidad en una
  sola línea** — no crear dos entradas separadas para el mismo producto. Por ejemplo: si
  agregas 2 unidades de un teclado y luego 3 más del mismo teclado, el carrito debe
  terminar con una sola línea de "5 teclados", no dos líneas de "2" y "3".
- Calcula un **total** que suma, por cada línea del carrito: `(precio unitario × cantidad)
  + impuesto de esa línea + envío de esa línea`. El impuesto y el envío de cada línea salen
  del propio producto (`taxRate()` / `shippingCost()`), nunca de un `if` en `Cart` que
  pregunte el tipo.

### 3. Validaciones de negocio

- **Cantidad inválida**: agregar 0 o una cantidad negativa debe rechazarse (no debe llegar
  a tocar el stock ni el carrito).
- **Stock insuficiente**: no se puede agregar más unidades de las que el producto tiene
  disponibles — y esto se evalúa contra **lo que ya hay acumulado en el carrito** para ese
  producto, no solo contra la cantidad de la llamada actual. Ejemplo: un producto con
  stock 5; agregas 3 (ok, quedan 2 disponibles); intentas agregar 3 más → debe rechazarse
  (solo quedaban 2).

## Restricciones (no son opcionales)

1. **Prohibido `if/else`/`switch` para diferenciar comportamiento por tipo de producto.**
   Si necesitas preguntar "¿es físico o digital?" en algún punto, el diseño está mal — debe
   resolverse con polimorfismo (cada subclase implementa su propio comportamiento).
2. **`equals`/`hashCode` correctos en `Product`**, basados en el identificador único (SKU).
   Se prueba solo: si están mal, agregar el mismo producto dos veces al carrito genera dos
   entradas en vez de acumular la cantidad.
3. **Ningún error de negocio puede reventar sin capturar.** Nada de `NullPointerException`,
   `ArrayIndexOutOfBoundsException` etc. propagándose hasta la consola. Los errores
   esperables (cantidad inválida, sin stock) se modelan con **excepciones propias** y un
   mensaje útil — nunca con un `catch` vacío ni tragándose el error.
4. **Nada de `null` como "no encontrado".** Si necesitas expresar ausencia, usa una
   excepción o `Optional`.
5. **Al menos 4 pruebas JUnit**, patrón AAA (Arrange–Act–Assert), cubriendo:
   - Acumular cantidad al agregar el mismo producto dos veces.
   - `InsufficientStockException` cuando se pide más de lo disponible (`assertThrows`).
   - `InvalidQuantityException` con cantidad cero o negativa (`assertThrows`).
   - El total cambia correctamente según el tipo de producto (impuesto/envío distintos).

## Usa lo que viste esta semana

Las restricciones de arriba son las únicas reglas fijas. Todo lo demás es libre — de
hecho, se espera que eches mano de otras herramientas que ya viste esta semana donde
tengan sentido: **Streams** para recorrer/sumar/transformar colecciones, etc. No hay una "forma correcta" única de
resolver el reto por dentro, mientras respetes las restricciones y la rúbrica.

## Punto de partida

El proyecto Maven ya está armado en [`../shop-cart-app/`](../shop-cart-app) con:

- `pom.xml` con JUnit 5 configurado.
- La jerarquía de clases (`Product`, `PhysicalProduct`, `DigitalProduct`, `Cart`,
  `InsufficientStockException`, `InvalidQuantityException`) como **stubs** — firmas y
  comentarios `TODO` que marcan qué falta, sin implementación.
- `CartTest` con los 4 casos de prueba ya nombrados y comentados, listos para escribirse.

No pierdas tiempo configurando: todo lo de infraestructura ya está. El diseño de las
clases (campos, constructores, cómo calcula cada cosa) es tuyo.

## Qué entregar

1. Las clases del dominio implementadas, respetando las 5 restricciones de arriba.
2. Las 4+ pruebas JUnit pasando (`./mvnw test` en verde).
3. Una explicación corta del código (comentario arriba de `Cart` o `.txt` aparte)
   respondiendo:
   - ¿Por qué modelaste `Product` como clase abstracta (o interfaz) y qué va en cada
     subclase?
   - ¿Qué garantiza que `equals`/`hashCode` sean consistentes entre sí?
   - ¿Qué decidiste que pasa si `Cart.total()` se llama sobre un carrito vacío?

## Rúbrica

- `./mvnw test` en verde.
- Cero `if/else`/`switch` distinguiendo tipo de producto (en `Cart` o donde sea).
- `equals`/`hashCode` en `Product` correctos y consistentes entre sí.
- Cero excepciones no controladas propagándose a consola; excepciones propias con mensaje
  útil para los casos de negocio.
- ≥ 4 pruebas JUnit, nombradas por lo que verifican, patrón AAA, con `assertThrows` donde
  aplica.
- Explicación del código presente y responde las 3 preguntas.

## Tarea (10 min)

Prepara la explicación del código (punto 3 de "qué entregar") para presentarla brevemente
la próxima sesión — 1-2 minutos por persona, enfocado en las decisiones de diseño, no en
narrar línea por línea.
