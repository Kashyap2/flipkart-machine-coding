import com.example.lld.model.{StringId, StringIdCompanion}

case class PaymentTicketId(override val value: String) extends StringId

object PaymentTicketId extends StringIdCompanion[PaymentTicketId] {
  override val prefix: String = "${NAME.dropRight(2)}" // Assumes name like "RideId", "UserId"
  override def apply(): PaymentTicketId = PaymentTicketId(StringId.generate(prefix))
}