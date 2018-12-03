/**
 * @author SangHoon, Lee(devsh@helloworlds.co.kr)
 */

let ObjectUtils = (function () {

	/************************************
	 Constructors
	 ************************************/
	function ObjectUtils(sourceObj) {
		this.sourceObj = sourceObj;
	}

	/************************************
	 Prototype
	 ************************************/
	ObjectUtils.prototype.getLastChainObjectValueByDotSeparator = getLastChainObjectValueByDotSeparator;

	/************************************
	 Functions
	 ************************************/
	/**
	 * let person = {
	 * 			gender: 'male',
	 * 			man: {
	 * 				sanghoon: {
	 * 					name: 'sanghoon',
	 * 					age: 29
	 * 				}
	 * 			}
	 * 		};
	 *
	 * let field = 'man.sanghoon';
	 *
	 * let objectUtil = new ObjectUtils(person);
	 * let sanghoon = objectUtil.getLastChainObjectValueByDotSeparator(field);
	 * console.log(sanghoon);
	 */
	function getLastChainObjectValueByDotSeparator(sourceField) {
		if (!sourceField) {
			return void 0;
		}

		if (sourceField.indexOf(".") === -1) {
			return void 0;
		}

		let lastChainObj;
		let fields = sourceField.split('.');
		let sourceObj = this.sourceObj;
		for (let i = 0; i < fields.length; i++) {
			let field = fields[i];
			if (i === fields.length - 1) {
				lastChainObj = sourceObj;
			} else {
				sourceObj = sourceObj[field];
			}
		}
		return lastChainObj;
	}

	return ObjectUtils;
}());


