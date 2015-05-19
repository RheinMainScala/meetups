package samples.traits.zoo

trait Carnivore extends CanHunt {
	override def toString = "is Carnivore " + super.toString
}