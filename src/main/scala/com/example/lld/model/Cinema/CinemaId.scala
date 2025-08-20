import com.example.lld.model.{StringId, StringIdCompanion}

case class CinemaId(override val value: String) extends StringId

object CinemaId extends StringIdCompanion[CinemaId] {
  override val prefix: String = "${NAME.dropRight(2)}" // Assumes name like "RideId", "UserId"
  override def apply(): CinemaId = CinemaId(StringId.generate(prefix))
}