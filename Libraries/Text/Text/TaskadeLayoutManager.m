/**
 * Copyright (c) Taskade 
*/

#import "TaskadeLayoutManager.h"

@implementation TaskadeLayoutManager

- (void)drawUnderlineForRect:(CGRect)rect
{
  UIBezierPath *path = [UIBezierPath new];
  path.lineWidth = 3.5;
  [path moveToPoint: CGPointMake(CGRectGetMinX(rect), CGRectGetMaxY(rect))];
  [path addLineToPoint: CGPointMake(CGRectGetMaxX(rect), CGRectGetMaxY(rect))];
  [path stroke];
}

- (void)drawUnderlineForGlyphRange:(NSRange)glyphRange 
                     underlineType:(NSUnderlineStyle)underlineVal 
                    baselineOffset:(CGFloat)baselineOffset 
                  lineFragmentRect:(CGRect)lineRect 
            lineFragmentGlyphRange:(NSRange)lineGlyphRange 
                   containerOrigin:(CGPoint)containerOrigin
{
  NSTextContainer *textContainer = [self textContainerForGlyphAtIndex:glyphRange.location effectiveRange: nil];
  CGRect boundingRect = [self boundingRectForGlyphRange:glyphRange inTextContainer:textContainer];
  CGRect offsetRect = CGRectOffset(boundingRect, containerOrigin.x, containerOrigin.y );
  UIColor *color = [self.textStorage attribute:NSUnderlineColorAttributeName atIndex:glyphRange.location effectiveRange: nil];
  
  if (color) {
    [color setStroke];
  }

  [self drawUnderlineForRect:offsetRect];
}

@end
