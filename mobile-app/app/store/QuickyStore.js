Ext.define('UGQuickies.store.QuickyStore', {
	extend: 'Ext.data.Store',
	config: {
	    model: 'UGQuickies.model.Quicky',
	    storeId: 'quickyStore',

	    proxy: {
	        type: 'ajax',
	        //url : 'http://sd-44145.dedibox.fr:8090/quickies',
	        url : 'http://localhost:8080/quickies',
	        reader: {
	            type : 'json'
	        }
	    },
	    autoLoad: true
    }
});