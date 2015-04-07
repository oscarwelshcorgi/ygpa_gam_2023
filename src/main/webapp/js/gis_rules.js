//건축 시설
var fcltyConstRuleSet = [
	{
		filter: new OpenLayers.Filter.Comparison({
			type: OpenLayers.Filter.Comparison.EQUAL_TO,
			property: "FCLTY_SE",
			value: "A"
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#45CEC9",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "운영건물,세관건물",
		filter: new OpenLayers.Filter.Comparison({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'A1'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#FF00FF",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'A5'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#FF00FF",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "복합건물",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'AC'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#FF00FF",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "마린센터,물류센터",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'AB'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#FF0000",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'AE'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#FF0000",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "휴게소",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'A2'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#FFFF00",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "근로자대기소",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'A6'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#FFFF00",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "편의시설",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'AG'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#FFFF00",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "화장실",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'AK'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#FFFF00",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "노조회관",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'AN'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#FFFF00",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "GATE-COMPLEX",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'A3'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#00FF00",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "경비실",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'AJ'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#00FF00",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "경비초소",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'AL'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#00FF00",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "청경실",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'AM'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#00FF00",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "홍보관",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'AF'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#0000FF",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "사무실",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'AI'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#0000FF",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "청사",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'AP'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#0000FF",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "CFS/CIS",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'A4'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#9900FF",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "창고",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'AH'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#9900FF",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "선좌변전소",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'A7'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#FF99CC",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "주변전소",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'AA'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#FF99CC",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "변전실",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'AO'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#FF99CC",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "정비공장",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'A8'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#66FF66",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "주유소",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'A9'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#66FF66",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "폐수처리조",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'AD'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#66FF66",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "기타",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'A'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'AZ'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#00FFFF",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	}
];

//기계 시설
var fcltyMechRuleSet = [
	{
		filter: new OpenLayers.Filter.Comparison({
			type: OpenLayers.Filter.Comparison.EQUAL_TO,
			property: "FCLTY_SE",
			value: "M"
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_${FCLTY_CD}.png",
				graphicWidth: 32,
				graphicHeight: 37,
				graphicXOffset: -16,
				graphicYOffset: -37,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			},
			"Line" : {
				strokeWidth : 3,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			},
			"Polygon" : {
				fillColor: "#FF9600",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#000080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: '컨테이너크레인',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'M'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'M1'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_M1.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333",
				label : "${FCLTY_NM}",
				fontColor: "green",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "ct",
				labelXOffset: 0,
				labelYOffset: -3,
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: '트렌스퍼크레인',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'M'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'M2'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_M2.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333",
				label : "${FCLTY_NM}",
				fontColor: "green",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "ct",
				labelXOffset: 0,
				labelYOffset: -3,
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: '트윈스프레더',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'M'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'M3'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_M3.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333",
				label : "${FCLTY_NM}",
				fontColor: "green",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "ct",
				labelXOffset: 0,
				labelYOffset: -3,
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: '하버크레인',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'M'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'M4'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_M4.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333",
				label : "${FCLTY_NM}",
				fontColor: "green",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "ct",
				labelXOffset: 0,
				labelYOffset: -3,
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: '야드트랙터',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'M'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'M5'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_M5.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333"
			}
		}
	},
	{
		title: '리치스텍커',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'M'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'M6'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_M6.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333"
			}
		}
	},
	{
		title: '탑핸들러',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'M'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'M7'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_M7.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333"
			}
		}
	},
	{
		title: '샷시',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'M'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'M8'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_M8.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333"
			}
		}
	},
	{
		title: '지게차',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'M'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'M9'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_M9.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333"
			}
		}
	},
	{
		title: '부잔교',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'M'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'MA'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#FF00FF",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#000080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: '건축 기계설비',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'M'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'MB'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#FF9600",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#000080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{	title:'기타',
		filter: new OpenLayers.Filter.Comparison({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'M'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'MZ'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_MZ.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333"
			},
			"Line" : {
				strokeWidth : 3,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			},
			"Polygon" : {
				fillColor: "#FF9600",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#000080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	}
];

//전기 시설
var fcltyPowerRuleSet = [
	{
		filter: new OpenLayers.Filter.Comparison({
			type: OpenLayers.Filter.Comparison.EQUAL_TO,
			property: "FCLTY_SE",
			value: "E"
		}),
		symbolizer: {
			"Point" : {
				strokeColor : "#333333",
				strokeOpacity : 1,
				strokeWidth : 1,
				fillColor : "red",
				fillOpacity : 1,
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_${FCLTY_CD}.png",
				graphicWidth: 32,
				graphicHeight: 37,
				graphicXOffset: -16,
				graphicYOffset: -37,
				label : "${FCLTY_NM}",
				fontColor: "blue",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "ct",
				labelXOffset: 0,
				labelYOffset: -3,
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			},
			"Line" : {
				strokeWidth : 3,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			},
			"Polygon" : {
				fillColor: "#45CEC9",
				fillOpacity: 0.5,
				strokeWidth : 3,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "변전소",
		filter: new OpenLayers.Filter.Comparison({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'E'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'E1'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#FF00FF",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "변전실",
		filter: new OpenLayers.Filter.Comparison({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'E'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'E2'
			          })
			          ]
		}),
		symbolizer: {
			"Polygon" : {
				fillColor: "#00FF00",
				fillOpacity: 0.5,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#800080",
				label : "${FCLTY_NM}",
				fontColor: "black",
				fontSize: "12px",
				fontFamily: "Courier New, monospace",
				fontWeight: "bold",
				labelAlign: "cm",
				labelXOffset: "2px",
				labelYOffset: "16px",
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: '수배전반',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'E'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'E3'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_E3.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333",
				label : "${FCLTY_NM}",
				fontColor: "blue",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "ct",
				labelXOffset: 0,
				labelYOffset: -3,
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: '조명탑 (4등용)',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'E'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'E6'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_E6.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333",
				label : "${FCLTY_NM}",
				fontColor: "blue",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "ct",
				labelXOffset: 0,
				labelYOffset: -3,
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: '조명탑 (4각탑)',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'E'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'E7'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_E7.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333",
				label : "${FCLTY_NM}",
				fontColor: "blue",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "ct",
				labelXOffset: 0,
				labelYOffset: -3,
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: '조명탑 (1POLE)',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'E'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'E8'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_E8.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333",
				label : "${FCLTY_NM}",
				fontColor: "blue",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "ct",
				labelXOffset: 0,
				labelYOffset: -3,
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: '가로등(1등)',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'E'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'E9'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_E9.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333",
				label : "",
				fontColor: "blue",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "ct",
				labelXOffset: 0,
				labelYOffset: -3,
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: '가로등(2등)',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'E'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'EA'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_EA.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333",
				label : "",
				fontColor: "blue",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "ct",
				labelXOffset: 0,
				labelYOffset: -3,
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'E'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'EB'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_EB.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333",
				label : "",
				fontColor: "blue",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "ct",
				labelXOffset: 0,
				labelYOffset: -3,
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'E'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'EC'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_EC.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333",
				label : "",
				fontColor: "blue",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "ct",
				labelXOffset: 0,
				labelYOffset: -3,
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: '주차등(1등)',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'E'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'ED'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_ED.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333",
				label : "",
				fontColor: "blue",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "ct",
				labelXOffset: 0,
				labelYOffset: -3,
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: '공원(1등)',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'E'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'EE'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_EE.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333",
				label : "",
				fontColor: "blue",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "ct",
				labelXOffset: 0,
				labelYOffset: -3,
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: '기타조명시설',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'E'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'EI'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_EI.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333",
				label : "",
				fontColor: "blue",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "ct",
				labelXOffset: 0,
				labelYOffset: -3,
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title:"전력선",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'E'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'EF'
			          })
			          ]
		}),
		symbolizer: {
			"Line" : {
				strokeWidth : 5,
				strokeOpacity : 0.75,
				strokeColor : "#FF0000",
				strokeLinecap: 'square',
				strokeDashstyle: 'solid',
			}
		}
	},{	  title:'기타',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'E'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'EZ'
			          })
			          ]
		  }),
		  symbolizer: {
			  "Point" : {
					pointRadius : 10,
					externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_EZ.png",
					graphicWidth: 16,
					graphicHeight: 19,
					graphicXOffset: -8,
					graphicYOffset: -19,
					fillColor : "red",
					fillOpacity : 1,
					strokeWidth : 1,
					strokeOpacity : 1,
					strokeColor : "#5432C9"
				},
			  "Line" : {
				  strokeWidth : 3,
				  strokeOpacity : 1,
				  strokeColor : "#800080"
			  },
			  "Polygon" : {
				  fillColor: "#45CEC9",
				  fillOpacity: 0.5,
				  strokeWidth : 3,
				  strokeOpacity : 1,
				  strokeColor : "#800080"
			  }
		  }
		}
];

//토목 시설
var fcltyCivilRuleSet = [
	{ 
	  filter: new OpenLayers.Filter.Comparison({
	      type: OpenLayers.Filter.Comparison.EQUAL_TO,
	      property: "FCLTY_SE",
	      value: "C"
	  }),
	  symbolizer: {
		  "Point" : {
			  pointRadius : 10,
			  externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_${FCLTY_CD}.png",
			  graphicWidth: 32,
			  graphicHeight: 37,
			  graphicXOffset: -16,
			  graphicYOffset: -37,
			  fillColor : "red",
			  fillOpacity : 1,
			  strokeWidth : 1,
			  strokeOpacity : 1,
			  strokeColor : "#333333"
		  },
		  "Line" : {
			  strokeWidth : 3,
			  strokeOpacity : 1,
			  strokeColor : "#000000"
		  },
		  "Polygon" : {
			  fillColor: "#45CEC9",
			  fillOpacity: 0.5,
			  strokeWidth : 3,
			  strokeOpacity : 1,
			  strokeColor : "#000000"
		  }
	  }
	},
	{
		title: "방충재",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'C'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'C1'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_C1.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#5432C9"
			}
		}
	},
	{
		title: "계선주",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'C'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'C2'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_C2.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#5432C9"
			}
		}
	},
	{
		title:"오수관로",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'C'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'C5'
			          })
			          ]
		}),
		symbolizer: {
			"Line" : {
				strokeWidth : 5,
				strokeOpacity : 0.75,
				strokeColor : "#FF7F27",
				strokeLinecap: 'square',
				strokeDashstyle: 'solid',
			}
		}
	},
	{
		title:"상수관로",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'C'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'C4'
			          })
			          ]
		}),
		symbolizer: {
			"Line" : {
				strokeWidth : 5,
				strokeOpacity : 0.75,
				strokeColor : "#0E25D1",
				strokeLinecap: 'square',
				strokeDashstyle: 'solid'
			}
		}
	},
	{
		title:"배수관로",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'C'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'C3'
			          })
			          ]
		}),
		symbolizer: {
			"Line" : {
				strokeWidth : 5,
				strokeOpacity : 0.75,
				strokeColor : "#BD00C9",
				strokeLinecap: 'square',
				strokeDashstyle: 'solid'
			}
		}
	},
	{	  title:'기타',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'C'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'CZ'
			          })
			          ]
		  }),
		  symbolizer: {
			  "Point" : {
					pointRadius : 10,
					externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_CZ.png",
					graphicWidth: 16,
					graphicHeight: 19,
					graphicXOffset: -8,
					graphicYOffset: -19,
					fillColor : "red",
					fillOpacity : 1,
					strokeWidth : 1,
					strokeOpacity : 1,
					strokeColor : "#5432C9"
				},
			  "Line" : {
				  strokeWidth : 3,
				  strokeOpacity : 1,
				  strokeColor : "#800080"
			  },
			  "Polygon" : {
				  fillColor: "#45CEC9",
				  fillOpacity: 0.5,
				  strokeWidth : 3,
				  strokeOpacity : 1,
				  strokeColor : "#800080"
			  }
		  }
		}
];

//정보통신 시설
var fcltyITRuleSet = [
	{
		filter: new OpenLayers.Filter.Comparison({
			type: OpenLayers.Filter.Comparison.EQUAL_TO,
			property: "FCLTY_SE",
			value: "I"
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
	//			externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/cctv.png",
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_${FCLTY_CD}.png",
				graphicWidth: 32,
				graphicHeight: 37,
				graphicXOffset: -16,
				graphicYOffset: -37,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#333333",
				label : "${FCLTY_NM}",
				fontColor: "green",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "cb",
				labelAlign: "cm",
				labelXOffset: 0,
				labelYOffset: -3,
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			},
			"Line" : {
				strokeWidth : 3,
				strokeOpacity : 1,
				strokeColor : "#800080"
			},
			"Polygon" : {
				fillColor: "#45CEC9",
				fillOpacity: 0.5,
				strokeWidth : 3,
				strokeOpacity : 1,
				strokeColor : "#800080"
			}
		}
	},
	{
		title: "스피드돔 카메라",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'I'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'I1'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_I1.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#5432C9",
				label : "${FCLTY_NM}",
				fontColor: "green",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "cb",
				labelAlign: "cm",
				labelXOffset: 0,
				labelYOffset: -3,
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "고정형 카메라",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'I'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'I2'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_I2.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#5432C9",
				label : "${FCLTY_NM}",
				fontColor: "green",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "cb",
				labelAlign: "cm",
				labelXOffset: 0,
				labelYOffset: -3,
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "RFID 차량용리더",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'I'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'I3'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_I3.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#5432C9",
				label : "${FCLTY_NM}",
				fontColor: "green",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "cb",
				labelAlign: "cm",
				labelXOffset: 0,
				labelYOffset: -3,
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{
		title: "RFID 인원용리더",
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'I'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'I4'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_I4.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#5432C9",
				label : "${FCLTY_NM}",
				fontColor: "green",
				fontSize: "12px",
				fontFamily: "Nanum Gothic",
				fontWeight: "thin",
				labelAlign: "cb",
				labelAlign: "cm",
				labelXOffset: 0,
				labelYOffset: -3,
				labelOutlineColor: "white",
				labelOutlineWidth: 3
			}
		}
	},
	{	title: '기타',
		filter: new OpenLayers.Filter.Logical({
			type: OpenLayers.Filter.Logical.AND,
			filters: [
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_SE",
			        	  value: 'I'
			          }),
			          new OpenLayers.Filter.Comparison({
			        	  type: OpenLayers.Filter.Comparison.EQUAL_TO,
			        	  property: "FCLTY_CD",
			        	  value: 'IZ'
			          })
			          ]
		}),
		symbolizer: {
			"Point" : {
				pointRadius : 10,
				externalGraphic: "/images/egovframework/ygpa/gam/maps/map_icon/icon_IZ.png",
				graphicWidth: 16,
				graphicHeight: 19,
				graphicXOffset: -8,
				graphicYOffset: -19,
				fillColor : "red",
				fillOpacity : 1,
				strokeWidth : 1,
				strokeOpacity : 1,
				strokeColor : "#5432C9",
			},
			"Line" : {
				strokeWidth : 3,
				strokeOpacity : 1,
				strokeColor : "#800080"
			},
			"Polygon" : {
				fillColor: "#45CEC9",
				fillOpacity: 0.5,
				strokeWidth : 3,
				strokeOpacity : 1,
				strokeColor : "#800080"
			}
		}
	}
];