# Shape Library
This is a shape library intended to be used to help work with geometric shapes. Currently only Rectangles are supported.

This is intended to be published and consumed as an Java library with limited dependencies.

## Useful commands
- `./gradlew check` - run the tests
- `./gradle build` - compiles for distribution

## Use
There are 3 main methods included in [`Library.java`](./src/main/java/interview/rectangle/Library.java) that provide the
main interface to determine adjacency, intersection and containment of rectangles. Additionally all models are contained 
within the `models` dir. These classes provide a number of utility functions as well to supplement your use case.

You'll first need to instantiate `Rectangle`s in order to work with any of the methods mentioned in `index.js`. The 
Rectangle class depends on the `Coordinate` class to define it's four corners as well as define the rectangle's edges
internally. Be sure to assign the correct coordinate to the correctly labelled argument when instantiating rectangles.