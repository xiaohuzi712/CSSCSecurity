Entities.Standrd = (function(Backbone, Entities,_) {
    var base = 'http://localhost:8080';
    var API_SAVE =base+'/standard/add';//添加标准管理
    var API_EDIT = base+'/standard/edit';//编辑标准管理
    var API_FETCH = base +'/standard/query';//查询标准管理
    var API_DESTROY = base+'/standard/delete';//删除标准管理
    var Model = Backbone.Model.extend({
        idAttribute: 'id',
        edit: function(data){
            var model = this;
            data = _.extend({id:model.id},data);
            console.log(model.id);
            return Entities.sync(API_EDIT,data).then(function(res){
                model.set(_.extend(data,res));
            });
        },
        delete: function(){
            var model = this;
            var data = {id:model.id};
            return Entities.sync(API_DESTROY,data).then(function(res){
                model.stopListening();
                model.trigger('destroy', model,model.collection,{ removeSelf: true });
            });
        },
    });

    var Collection = Backbone.Collection.extend({
        model:Model,
        fetch: function(){
            var collection=this;
            return Entities.sync(API_FETCH).then(function(res){
                collection.reset(res);
            });
        },
        add: function(data){
            var collection=this;
            return Entities.sync(API_SAVE,data).then(function(res){
                collection.unshift(_.extend(data,res));
            });
        }
    });
    return {
        Model: Model,
        Collection: Collection
    };
}(Backbone,Entities,_));
