package domain

class Account(var name: String) {

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        if (other !is Account) {
            return false
        }

        return other.name == this.name

    }
}