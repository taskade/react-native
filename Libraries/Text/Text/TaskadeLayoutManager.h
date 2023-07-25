/**
 * Copyright (c) Taskade 
 * A custom layout manager to support gap-less underlined text
*/

#import <UIKit/UIKit.h>

@interface TaskadeLayoutManager : NSLayoutManager 

- (void)drawUnderlineForRect:(CGRect)rect;

- (void)drawUnderlineForGlyphRange:(NSRange)glyphRange 
                underlineType:(NSUnderlineStyle)underlineVal 
                baselineOffset:(CGFloat)baselineOffset 
                lineFragmentRect:(CGRect)lineRect 
                lineFragmentGlyphRange:(NSRange)lineGlyphRange 
                containerOrigin:(CGPoint)containerOrigin;
@end
