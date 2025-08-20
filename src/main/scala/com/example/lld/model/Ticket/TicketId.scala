import com.example.lld.model.{StringId, StringIdCompanion}

case class Ticketid(override val value: String) extends StringId

object Ticketid extends StringIdCompanion[Ticketid] {
  override val prefix: String = "${NAME.dropRight(2)}" // Assumes name like "RideId", "UserId"
  override def apply(): Ticketid = Ticketid(StringId.generate(prefix))
}