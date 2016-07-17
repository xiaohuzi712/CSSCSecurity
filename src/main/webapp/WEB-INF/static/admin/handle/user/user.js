/**
 * Created by huchao on 2016/7/6.
 */
Entities.User = (function (Backbone, Entities, _) {
    var base = Entities.config.apiUrl;
    var API_SAVE = base + '/user/add';//添加法律法规
    var API_EDIT = base + '/user/edit';//编辑法律法规
    var API_QUERY = base + '/user/queryDetail';//查询指定id内容
    var API_FETCH = base + '/user/query';//查询法律法规
    var API_DESTROY = base + '/user/delete';//删除法律法规
    var API_DELETES = base + '/user/deletes';//删除法律法规
    var Model = Backbone.Model.extend({
        idAttribute: 'id',
        edit: function (data) {
            var model = this;
            data = _.extend({id: model.id}, data);
            return Entities.sync(API_EDIT, data).then(function (res) {
                model.set(_.extend(data, res));
            });
        },
        delete: function () {
            var model = this;
            var data = {id: model.id};
            return Entities.sync(API_DESTROY, data).then(function (res) {
                model.trigger('destroy', model, model.collection, {removeself: true});
            });
        }
    });

    var Collection = Backbone.Collection.extend({
        model: Model,
        fetch: function (data) {
            var collection = this;
            return Entities.sync(API_FETCH, data).then(function (res) {
                collection.reset(res);
            });
        },
        query: function (data) {
            var collection = this;
            return Entities.sync(API_QUERY, data).then(function (res) {
                collection.reset(res);
            });
        },
        create: function (data) {
            var collection = this;
            return Entities.sync(API_SAVE, data).then(function (res) {
                collection.unshift(_.extend(data,res));
            });
        },
        deletes: function (data) {
            var collection = this;
            return Entities.sync(API_DELETES, data).then(function (res) {
                collection.unshift(_.extend(data,res));
            });
        }
    });
    return {
        Model: Model,
        Collection: Collection
    };
}(Backbone, Entities, _));
