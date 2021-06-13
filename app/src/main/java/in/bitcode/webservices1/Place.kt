package `in`.bitcode.webservices1

class Place {
    var name : String? = null
    var address : String? = null
    var imageUrl : String? = null
    var rating : Double = 0.0
    var lat : Double = 0.0
    var lng : Double = 0.0
    var status : Boolean = true

    override fun toString(): String {
        return "$name -->> $address $rating $status"
    }
}
