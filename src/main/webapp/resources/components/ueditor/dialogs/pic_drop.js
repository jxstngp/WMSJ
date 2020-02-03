/*
 * HTML5 Sortable jQuery Plugin
 * http://farhadi.ir/projects/html5sortable
 * 
 * Copyright 2012, Ali Farhadi
 * Released under the MIT license.
 */
(function($) {
var dragging, placeholders = $();

$.fn.sortable = function(options) {
	/**让li随子元素大小改变大小**/
	var liheight = (parseInt($(this).find('dd,li').children().css('height'))+2)+'px',
		liwidth = (parseInt($(this).find('dd,li').children().css('width'))+2)+'px',
		placeholderHeight = (parseInt(liheight))+'px',
		placeholderWidth = (parseInt(liwidth))+'px';
	$(this).find('dd,li').height(liheight).width(liwidth).css('border','1px solid none;');
	/** end**/
	options = options || {};
	return this.each(function() {
		
		if (/^enable|disable|destroy$/.test(options)) {
			var items = $(this).children($(this).data('items')).attr('draggable', options == 'enable');
			options == 'destroy' &&	items.add(this)
				.removeData('connectWith').removeData('items')
				.unbind('dragstart.h5s dragend.h5s selectstart.h5s dragover.h5s dragenter.h5s drop.h5s');
			return;
		}
		
		var index, items = $(this).children(options.items), connectWith = options.connectWith || false;
		var placeholder = $('<' + items[0].tagName + ' class="sortable-placeholder">');
		/**给生成的占位加个虚框**/
		$(placeholder).height(placeholderHeight).width(placeholderWidth).css('border','1px dashed blue');
		/**end**/
		var handle = options.handle, isHandle;
		items.find(handle).mousedown(function() {
			isHandle = true;
		}).mouseup(function() {
			isHandle = false;
		});
		
		$(this).data('items', options.items);
		placeholders = placeholders.add(placeholder);
		if (connectWith) {
			$(connectWith).add(this).data('connectWith', connectWith);
		}
		items.attr('draggable', 'true').bind('dragstart.h5s', function(e) {
			if (handle && !isHandle) {
				return false;
			}
			isHandle = false;
			var dt = e.originalEvent.dataTransfer;
			dt.effectAllowed = 'move';
			dt.setData('Text', 'dummy');
			dragging = $(this).addClass('.sortable-dragging');
			index = dragging.index();
		}).bind('dragend.h5s', function() {
			//console.log(dragging.hasClass(".sortable-dragging"));
			
			dragging.removeClass('.sortable-dragging').fadeIn();	
			placeholders.detach();
			if (index != dragging.index()) {
				items.parent().trigger('sortupdate');
			}
			dragging = null;
		}).not('a[href], img').bind('selectstart.h5s', function() {
			this.dragDrop && this.dragDrop();
			return false;
		}).end().add([this, placeholder]).bind('dragover.h5s dragenter.h5s drop.h5s', function(e) {
			if (!items.is(dragging) && connectWith !== $(dragging).parent().data('connectWith')) {
				return true;
			}
			if (e.type == 'drop') {
				e.stopPropagation();
				placeholders.filter(':visible').after(dragging);
				return false;
			}
			e.preventDefault();
			e.originalEvent.dataTransfer.dropEffect = 'move';
			if (items.is(this)) {
				dragging.hide();
				$(this)[placeholder.index() < $(this).index() ? 'after' : 'before'](placeholder);
				placeholders.not(placeholder).detach();
			}
			return false;
		});
		
	});
	
};
})(jQuery);