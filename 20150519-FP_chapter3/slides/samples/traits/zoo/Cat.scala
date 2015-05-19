package samples.traits.zoo

class Cat extends Animal with Mammal with Carnivore {
	override def toString = "Cat " + super.toString
}