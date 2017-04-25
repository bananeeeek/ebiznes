/**
  * Created by BanaN on 4/14/2017.
  */

abstract class ClonablePrototype extends Cloneable
{
  @throws[CloneNotSupportedException]
override def clone(): ClonablePrototype = super.clone.asInstanceOf[ClonablePrototype]
}

class ProductPrototype extends ClonablePrototype
{
  @throws[CloneNotSupportedException]
  override def clone(): ClonablePrototype = super.clone

  def method1(): Unit = {}

}
