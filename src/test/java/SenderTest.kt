import org.junit.Assert.assertNotNull
import org.junit.Test

class SenderTest {

    val sender : Sender = Sender(name = "Alice")

    @Test
    fun `should define name`() {
        assertNotNull(sender)
    }

}