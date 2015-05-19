package samples.traits.zoo

class Dolphin extends Animal with CanSwim with Mammal {
	override def toString = "Dolphin " + super.toString
}