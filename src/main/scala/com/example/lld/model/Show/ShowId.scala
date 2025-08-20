import com.example.lld.model.{StringId, StringIdCompanion}

case class ShowId(override val value: String) extends StringId

object ShowId extends StringIdCompanion[ShowId] {
  override val prefix: String = "${NAME.dropRight(2)}" // Assumes name like "RideId", "UserId"
  override def apply(): ShowId = ShowId(StringId.generate(prefix))
}