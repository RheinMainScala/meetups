package samples.traits.zoo

class Dog extends Animal with CanSwim with Mammal with Carnivore {
	override def toString = "Dog " + super.toString
}