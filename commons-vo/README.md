# commons-vo

goal: busco dominar el patron VO y saber usarlo donde sea.



HECHOS
- The ValueObject is immutable, 
	*sus campos deben ser finals. se crea todo en el constructor. no hay forma de editar.
	*Once the VO has no identity its integrity is based on its values, that’s why it’s important that the VO is immutable.
- The ValueObject must be compared by its value
	no hay identidad como el id de la base de datos.
	We subscribe the Equals method and implement the operators = = and != defining its own equality rules. Attention to null comparison.
Personalize the HashCode
	Subscribe the GetHashCode method using the same properties from equality to generate the HashCode. 
	Then our object can be found in a Dictionary or HashTable.


SEARCHING
https://medium.com/@hermesmonteiro1981/valueobject-pattern-when-to-use-identify-pattern-situation-e753292113c7
- The ValueObject Pattern proposes a solution for objects that conceptually have no identity, or that the “default” identity doesn’t serve to identify it and therefore the comparison operations should be performed by properties value instead of your “default” identity. (See Identity concept)
example:class Address tiene (Street city stated)