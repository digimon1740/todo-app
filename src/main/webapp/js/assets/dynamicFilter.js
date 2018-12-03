/**
 * @author SangHoon, Lee(devsh@helloworlds.co.kr)
 *
 * dependency objectUtil.js
 *
 */
let DynamicFilter = (function () {

	/************************************
	 Constructors
	 ************************************/
	function DynamicFilter(source) {
		this.source = source;
		this.objectUtil = new ObjectUtils(this.source);
	}

	/************************************
	 Prototype
	 ************************************/
	DynamicFilter.prototype.contains = contains;
	DynamicFilter.prototype.containsAll = containsAll;

	/************************************
	 Functions
	 ************************************/
	function contains(filterField, value) {
		let compareValue = this.source[filterField];
		if (filterField && filterField.indexOf('.') !== -1) {
			let fields = filterField.split('.');
			let field = fields[fields.length - 1];

			let lastChainObj = this.objectUtil.getLastChainObjectValueByDotSeparator(filterField);
			compareValue = lastChainObj[field];
		}

		switch (typeof compareValue) {
			case 'number' :
				if (compareValue == value) {
					return true;
				}
				break;
			case 'string' :
				// start with search (LIKE 'prefix%')
				if (compareValue.indexOf(value) === 0) {
					return true;
				}
				break;
			case 'boolean' :
				if (compareValue == JSON.parse(value)) {
					return true;
				}
				break;
		}
		return false;
	}

	function containsAll(where) {
		if (!where) {
			return false;
		}

		let self = this;
		let contains = true;
		let action;
		let andFields = where['andFields'], andValues = where['andValues'];
		if (andFields && andValues) {
			action = 'and';
			contains = filterBy(action, andFields, andValues);
		}

		let orFields = where['orFields'], orValues = where['orValues'];
		if (orFields && orValues) {
			action = 'or';
			contains = filterBy(action, orFields, orValues);
		}

		function filterBy(action, fields, values) {
			if (!Array.isArray(fields) || !Array.isArray(values)) {
				return false;
			}
			if (fields.length !== values.length) {
				return false;
			}

			let contains = true;
			let trueCnt = 0;

			for (let i = 0; i < fields.length; i++) {
				let filterField = fields[i];
				let value = values[i];
				if (!value || value === 'all') {
					if (action === 'or')
						trueCnt++;
					continue;
				}``

				if (action === 'and') {
					if (!self.contains(filterField, value)) {
						contains = false;
						break;
					}
				} else if (action === 'or') {
					if (self.contains(filterField, value)) {
						trueCnt++;
					}
				}
			}

			if (action === 'or') {
				if (trueCnt === 0)
					contains = false;
				else
					contains = true;
			}

			return contains;
		}

		return contains;
	}

	return DynamicFilter;
}());
