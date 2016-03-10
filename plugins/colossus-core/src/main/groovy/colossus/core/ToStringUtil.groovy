package colossus.core

trait ToStringUtil
{
	static final def excludes = ['class', 'constraints', 'constraintsMap', 'errors', 'excludes', 'log', 'mapWith', 'mapping', 'properties']

	def toMap()
	{
		this.properties.inject([:]) { a, b ->
			if ( ! ( b.key in excludes ) ) {
				a[b.key] = b.value
			}
			a
		}
	}

	@Override
	String toString()
	{
		"${this.class.name}${this.toMap()}"
	}
}