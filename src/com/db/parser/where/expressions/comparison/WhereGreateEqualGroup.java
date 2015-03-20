package com.db.parser.where.expressions.comparison;

import java.util.List;

import com.db.parser.where.ValueType;
import com.db.stucture.Value;

public class WhereGreateEqualGroup extends ComparisonExpression{

	@Override
	public boolean check(List<Value> values) {
		for (Value value : values) {
			if (value.field.name.equals(getField().getFieldName()))
				if (value.field.type == ValueType.DOUBLE.val) {
					if ((Long) value.value >= Double.parseDouble(getValue().getValue().toString()))
						return true;
					/* else if (getValue().getType() == ValueType.STRING) {
						if (((String) value.value).equals((String) getValue().getValue()))
							return true;
					}*/
				}
		}
		return false;
	}

}
