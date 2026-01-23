Java 25 — Flexible Constructors (01_flexible_constructors)

This example demonstrates flexible constructors that allow executing statements before delegating to another constructor, including before calling `super(...)`.

What this enables
- Validate and normalize constructor parameters prior to delegating to a superclass.
- Perform logging/metrics or compute derived values before the `super(...)` call.

Demo files
- `01_flexible_constructors/src/FlexibleConstructorsDemo.java`

Run it (from project root, Windows PowerShell):
```
java 01_flexible_constructors\src\FlexibleConstructorsDemo.java
```

Expected output (abridged):
```
Before super, normalized name=Alice
Person(super) called with name=Alice
After super, id=42
Result: Alice, id=42
```

Notes
- This example uses simple normalization and logging prior to the `super(...)` call to highlight the flow.
- Adjust and experiment with different inputs to see the order of execution.
