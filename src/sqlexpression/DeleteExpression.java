/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlexpression;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author tan
 */
public class DeleteExpression extends SqlExpression {
    private Map<String, Object> _deletes;
    
    public DeleteExpression(Connection c){
        super(c);
        _deletes = new LinkedHashMap<>();
    }
    
    public DeleteExpression(Connection c, String t){
        this(c);
        this.setFrom(t);
    }
    
    public DeleteExpression(Connection c, String t, Map<String, Object> values){
        this(c, t);
        _deletes = values;
    }
    
    public void addDelete(String k, Object v){
        if(_deletes.containsKey(k))
            return;
        _deletes.put(k, v);
    }

    @Override
    protected ResultSet execute(Connection _connection) throws SqlExpressionException {
        StringBuilder builder = new StringBuilder();
        Iterator<String> deleteKeySet = _deletes.keySet().iterator();
        
        String deleteKey = deleteKeySet.next();
        Object deleteValue = _deletes.get(deleteKey);
        builder.append("DELETE FROM ").append(this.getFrom())
                .append(" WHERE ").append(deleteKey).append("=");
        // check if deleteValue is non-numeric
        if(deleteValue.getClass().isInstance(String.class) == false)
            throw new UnsupportedOperationException();
        throw new UnsupportedOperationException();
    }

    @Override
    protected boolean hasResultSet() {
        return false;
    }
    
    public Map getDeletes(){
        return _deletes;
    }
    
    @Override
    protected boolean validateExpression() throws SqlExpressionException {
        boolean retVal = super.validateExpression();
        
        if(getDeletes().isEmpty())
            retVal = false;
        
        return retVal;
    }
    
}
